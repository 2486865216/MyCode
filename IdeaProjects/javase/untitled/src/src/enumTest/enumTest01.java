package src.enumTest;

public class enumTest01 {

    public static void main(String[] args) {
//        NullPointerException nullPointerException = new NullPointerException("hello word");
//        System.out.println(nullPointerException.getMessage());

        test();
        try {
            test2();
        }catch(BusinessException e){
            System.out.println(e.getResult().getMessage());
        }
    }
    static void test() {
        try {
            throw new BusinessException(Result.success);
        }catch(BusinessException e){
            e.printStackTrace();
            System.out.println(e.getResult().getMessage());
        }
    }
    static void test2() throws BusinessException{
        throw new BusinessException(Result.fail);
    }
}

enum Result{
    success(100, "ture"),
    fail(101, "false");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
class BusinessException extends RuntimeException{
    private Result result;
    private int code;
    private String message;

    public Result getResult() {
        return result;
    }

    public BusinessException(Result result){
        this.result = result;
        this.code = result.getCode();
        this.message = result.getMessage();
    }
}
