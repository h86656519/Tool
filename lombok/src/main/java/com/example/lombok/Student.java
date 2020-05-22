package com.example.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter  //這樣就不用寫getter
//@Setter  //這樣就不用寫setter
@Data    //這樣就不用寫getter setter
@Builder
@ToString(exclude = "id") //故意排除id
@EqualsAndHashCode //因為java 規定: object equals相同時hashCode 會相等，所以要寫在一起
public class Student {
    String id;
    String phone;
    String sex;
}
