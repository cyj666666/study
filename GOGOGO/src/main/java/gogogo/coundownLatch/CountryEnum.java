package gogogo.coundownLatch;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @Author: caoyunji
 * @Date: 2020/5/19 16:19
 */
@AllArgsConstructor
public enum CountryEnum {
    A(1, "齐"),
    B(2, "楚"),
    C(3, "韩"),
    D(4, "燕"),
    E(5, "赵"),
    F(6, "魏");

    @Getter
    private int index;

    @Getter
    private String countryName;

    public static String getEnum(int code) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (Objects.equals(code, value.getIndex())) {
                return value.getCountryName();
            }
        }
        return null;

    }

}
