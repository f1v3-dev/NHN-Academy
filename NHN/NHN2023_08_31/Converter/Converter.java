public class Converter {

    public static int binaryToDecimal(String binary) {
    
        int index = 1;
        int result = 0;

        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                result += index;
            }
            index *= 2;
        }

        return result;
    }

    public static String decimalToBinary(int decimal) {
        String result = "";
        while (decimal > 0) {
            if (decimal % 2 == 0) {
                result = "0" + result;
            }
            else {
                result = "1" + result;
            }
            decimal /= 2;
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(binaryToDecimal("1100111"));
        System.out.println(decimalToBinary(103));
    }
}