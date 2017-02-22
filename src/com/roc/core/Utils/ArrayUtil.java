package com.roc.core.Utils;

/**
 * User: rocwu
 * Date: 2016-10-4
 * Time: 7:10 pm
 * Desc: 字符类型
 */
public class ArrayUtil {

    public static int firstIndexOf(Object[] arr, Object target) {
        if (arr == null || arr.length == 0)
            return -1;
        for (int i = 0; i < arr.length; i++) {
            if (target.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public static String implode(Object[] array, String spliter) {
        StringBuilder sb = new StringBuilder();
        if (array == null || array.length == 0) return null;
        for (Object item : array) {
            sb.append(spliter).append(item);
        }
        return sb.substring(spliter.length());
    }

    public static Object explode(String str, String spliter) {
        if (str == null) return null;
        return str.split(spliter);
    }

    public static double calcCosSimilarity(double[] arr1, double[] arr2) {
        int len1 = arr1 == null ? 0 : arr1.length;
        int len2 = arr2 == null ? 0 : arr2.length;
        if (len1 != len2) throw new RuntimeException("different lens");
        int len = len1;
        if (len == 0) return 0.0;
        double simi = 0.0;
        double strlen1 = 0.0, strlen2 = 0.0;
        for (int i = 0; i < len; i++) {
            simi += arr1[i]*arr2[i];
            strlen1 += Math.pow(arr1[i], 2);
            strlen2 += Math.pow(arr2[i], 2);
        }
        strlen1 = Math.sqrt(strlen1);
        strlen2 = Math.sqrt(strlen2);
        double res = simi/(strlen1*strlen2);
        return res;
    }
}
