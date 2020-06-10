package com.example.signatureview

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi

class SignatureViewKt : View {
    private var paint: Paint
    private var paintBm: Paint
    private var MIN_PEN_SIZE: Float = 1f
    private var MIN_INCREMENT: Float = 0.01f
    private var INCREMENT_CONSTANT: Float = 0.0005f
    private var DRAWING_CONSTANT: Float = 0.0085f
    private var MAX_VELOCITY_BOUND: Float = 15f
    private var MIN_VELOCITY_BOUND: Float = 1.6f
    private var STROKE_DES_VELOCITY: Float = 1.0f
    private var VELOCITY_FILTER_WEIGHT: Float = 0.2f
    private var penColor: Int = 0
    private var penSize: Float = 0f
    private var enableSignature: Boolean = false
    private var ignoreTouch: Boolean = false
    private var myBackgroundColor: Int = 0
    private var layoutLeft: Int = 0
    private var layoutTop: Int = 0
    private var layoutRight: Int = 0
    private var layoutBottom: Int = 0
    private var bmp: Bitmap
    private var canvasBmp: Canvas?
    private var drawViewRect: Rect = Rect(this.getLeft(), this.getTop(), this.getRight(), this.getBottom())
    private var startPoint: Point?
    private var currentPoint: Point
    private var previousPoint: Point
    private var lastWidth: Float = 0f
    private var lastVelocity: Float = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        var typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ActionBar, 0, 0)
        try {
            myBackgroundColor = Color.WHITE;
            penColor = Color.BLACK;
            penSize = 30f
        } finally {
            typedArray.recycle()
        }

        paint.setColor(penColor)
        paint.setAntiAlias(true)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = penSize

        paintBm = Paint()
        paintBm = Paint(Paint.ANTI_ALIAS_FLAG)
        paintBm.setAntiAlias(true)
        paintBm.setStyle(Paint.Style.STROKE)
        paintBm.setStrokeJoin(Paint.Join.ROUND)
        paintBm.setStrokeCap(Paint.Cap.ROUND)
        paintBm.setColor(Color.BLACK)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {

    }

    init {
        paint = Paint()
        paintBm = Paint()
        paint.setColor(Color.BLACK)
        paint.strokeWidth = 10f
        val width = Resources.getSystem().displayMetrics.widthPixels
        bmp = Bitmap.createBitmap(width, 800, Bitmap.Config.ARGB_8888)
        canvasBmp = Canvas(bmp!!)
        currentPoint = Point(0f, 0f, System.currentTimeMillis())
        startPoint = Point(0f, 0f, System.currentTimeMillis())
        previousPoint = Point(0f, 0f, System.currentTimeMillis())


    }

    fun getPenSize(): Float {
        return penSize
    }

    fun setsetPenSize(penSize: Float) {
        this.penSize = penSize
    }

    fun isEnableSignature(): Boolean {
        return enableSignature
    }

    fun setEnableSignature(enableSignature: Boolean) {
        this.enableSignature = enableSignature
    }

    fun getPenColor(): Int {
        return penColor
    }

    fun setPenColor(penColor: Int) {
        this.penColor = penColor
        paint.color = penColor
    }

    fun getBackgroundColor(): Int {
        return this.myBackgroundColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bmp, 0f, 0f, paintBm)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        layoutLeft = left
        layoutTop = top
        layoutRight = right
        layoutBottom = bottom
        if (bmp == null) {
            newBitmapCanvas(layoutLeft, layoutTop, layoutRight, layoutBottom)
        }
    }

    fun newBitmapCanvas(left: Int, top: Int, right: Int, bottom: Int) {
//        bmp = null
        canvasBmp = null
        if ((right - left) > 0 && (bottom - top) > 0) {
            bmp = Bitmap.createBitmap(right - left, bottom - top, Bitmap.Config.ARGB_8888);
            canvasBmp = Canvas(bmp!!)
            canvasBmp!!.drawColor(myBackgroundColor);
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                ignoreTouch = false
                drawViewRect = Rect(this.getLeft(), this.getTop(), this.getRight(), this.getBottom())
                onTouchDownEvent(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                if (!drawViewRect.contains(getLeft() + event.getX().toInt(), getTop() + event.getY().toInt())) {
                    if (!ignoreTouch) {
                        ignoreTouch = true;
                        onTouchUpEvent(event.getX(), event.getY());
                    }
                } else {
                    //You are in the drawing area
                    if (ignoreTouch) {
                        ignoreTouch = false;
                        onTouchDownEvent(event.getX(), event.getY());
                    } else {
                        onTouchMoveEvent(event.getX(), event.getY());
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                onTouchUpEvent(event.getX(), event.getY());
            }
        }
        return true
    }

    fun onTouchMoveEvent(x: Float, y: Float) {
        if (previousPoint == null) {
            return;
        }
        startPoint = previousPoint;
        previousPoint = currentPoint;
        currentPoint = Point(x, y, System.currentTimeMillis());

        var velocity: Float = currentPoint!!.velocityFrom(previousPoint)
        velocity = VELOCITY_FILTER_WEIGHT * velocity + (1 - VELOCITY_FILTER_WEIGHT) * lastVelocity

        var strokeWidth: Float = getStrokeWidth(velocity)
        drawLine(lastWidth, strokeWidth, velocity)

        lastVelocity = velocity
        lastWidth = strokeWidth

        postInvalidate()
    }

    fun onTouchDownEvent(x: Float, y: Float) {
        lastVelocity = 0f
        lastWidth = penSize

        currentPoint = Point(x, y, System.currentTimeMillis())
        previousPoint = currentPoint
        startPoint = previousPoint

//        invalidate() //  6/6 會從一呼叫一次onDraw，直接在ui線程做調用，所以要在handler 裡面用，不能直接用
        postInvalidate()//在workthread 做調用，不會直接更新，裡面有handle send
    }

    fun onTouchUpEvent(x: Float, y: Float) {
        if (previousPoint == null) {
            return;
        }
        startPoint = previousPoint;
        previousPoint = currentPoint;
        currentPoint = Point(x, y, System.currentTimeMillis());

        drawLine(lastWidth, 0f, lastVelocity);
        postInvalidate();
    }

    fun drawLine(lastWidth: Float, currentWidth: Float, velocity: Float) {
        var mid1: Point = midPoint(previousPoint!!, startPoint!!)
        var mid2: Point = midPoint(currentPoint!!, previousPoint!!)
        draw(mid1, previousPoint!!, mid2, lastWidth,
                currentWidth, velocity);
    }

    fun midPoint(p1: Point, p2: Point): Point {
        return Point((p1.x + p2.x) / 2.0f, (p1.y + p2.y) / 2, (p1.time + p2.time) / 2)
    }


    fun draw(p0: Point, p1: Point, p2: Point, lastWidth: Float, currentWidth: Float, velocity: Float) {
        if (canvasBmp != null) {
            var xa: Float
            var xb: Float
            var ya: Float
            var yb: Float
            var x: Float
            var y: Float
            var increment: Float
            if (velocity > MIN_VELOCITY_BOUND && velocity < MAX_VELOCITY_BOUND) {
                increment = DRAWING_CONSTANT - (velocity * INCREMENT_CONSTANT)
            } else {
                increment = MIN_INCREMENT;
            }

            var i = 0f
            while (i < 1f) {
                xa = getPt(p0.x, p1.x, i)
                ya = getPt(p0.y, p1.y, i)
                xb = getPt(p1.x, p2.x, i)
                yb = getPt(p1.y, p2.y, i)

                x = getPt(xa, xb, i)
                y = getPt(ya, yb, i)
                var strokeVal: Float = lastWidth + (currentWidth - lastWidth) * (i)
                paint.setStrokeWidth(if (strokeVal < MIN_PEN_SIZE) MIN_PEN_SIZE else strokeVal)
                canvasBmp!!.drawPoint(x, y, paint)
                i = i + increment
            }
        }
    }

    fun getPt(n1: Float, n2: Float, perc: Float): Float {
        var diff = n2 - n1
        return n1 + (diff * perc)
    }

    fun getStrokeWidth(velocity: Float): Float {
        return penSize - (velocity * STROKE_DES_VELOCITY)
    }

    class Point(x: Float, y: Float, time: Long) {
        var x = 0f
        var y = 0f
        var time: Long = 0

        init {
            this.x = x
            this.y = y
            this.time = time
        }

        fun velocityFrom(start: Point): Float {
            return distanceTo(start) / (this.time - start.time)
        }

        private fun distanceTo(start: Point): Float {
            return Math.sqrt(Math.pow((x - start.x).toDouble(), 2.0) + Math.pow((y - start.y).toDouble(), 2.0)).toFloat()
        }


    }

    fun clearnView() {
        invalidate()
//        postInvalidate()

    }
}
