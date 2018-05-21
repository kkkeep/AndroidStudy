package esbook.com.testskin;

import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.VisibleForTesting;

/**
 * Created by taofu
 * on 2018/5/7.
 */
public class TestAutoInsertNullnessAnnotations {

    private String msg;

    public static final int MODEL_1 = 1;
    public static final int MODEL_2 = 1 << 1;
    public static final int MODEL_3 = 2 << 1;

    @IntDef(flag = true,value = {MODEL_1,MODEL_2,MODEL_3})
    public @interface MODEL{}

    @NonNull
    public String test(@NonNull String args){

            System.out.println(args.toLowerCase());

             getMsg();
            return args;

    }

    @CheckResult(suggest = "#getMsg1()") // 提示用户返回值没有被使用，如果不使用返回值可以考虑使用建议的方法
    public String getMsg() {
       return msg;
    }

    public void getMsg1(){
    }
    public String  getString(@StringRes @IdRes int id){

        return null;
    }


    @MODEL
    public int getModel(){

        return MODEL_1 | MODEL_2;
    }

    @VisibleForTesting()
    public void test1(){

    }

    // protected 修饰不够严格，因为除了能在子类中访问以外，如果再同一个包中也能访问，
    // RestrictTo.Scope.SUBCLASSES 限制了只能在子类中访问
    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
     protected void test2(){

    }



}

class Sub extends  TestAutoInsertNullnessAnnotations{

    public void test(){

        TestAutoInsertNullnessAnnotations testAutoInsertNullnessAnnotations = new TestAutoInsertNullnessAnnotations();

        testAutoInsertNullnessAnnotations.test2();
    }

    @Override
    protected void test2() {
        super.test2();
    }
}
