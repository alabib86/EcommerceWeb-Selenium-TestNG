package Luma.LumaWeb.Component;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count=0;
    int retryNum=1;
    @Override
    public boolean retry(ITestResult result) {
        if (count<retryNum){
            count++;
            return  true;
        }
        return false;
    }
}
