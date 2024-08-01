package com.qa.gorest.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener implements ITestListener {
    private static final String OUTPUT_FOLDER = "./reports/";
    private static final String FILE_NAME ="APIExecutionReport.html";

    private static ExtentReports extent = init();
    public static ThreadLocal< ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;

    private static ExtentReports init(){
        Path path =  Paths.get(OUTPUT_FOLDER);

        if(!Files.exists(path)){
            try{
                Files.createDirectories(path);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER+FILE_NAME);
        reporter.config().setReportName("API Test Results");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System","MAC");
        extentReports.setSystemInfo("Author","Pooja Rajput");
        extentReports.setSystemInfo("Build#","1.0");
        extentReports.setSystemInfo("Team","QA Team");
        extentReports.setSystemInfo("Customer Name","NA");
        return extentReports;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());

        /*
         * Optional: If you want to capitalize the method name or split it into words,
         * you can uncomment and use the following lines:
         *
         * methodName = StringUtils.capitalize(
         *     StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
         */

        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));

    }
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending");
        extent.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " passed!");
        test.get().pass("Test passed");
        // Uncomment the following line if you want to attach a throwable or a screenshot
        // test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " failed!");
        String methodName = result.getMethod().getMethodName();
        // Uncomment the following line if you want to attach a throwable or a screenshot
        // test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " skipped!");
        String methodName = result.getMethod().getMethodName();
        // Uncomment the following line if you want to attach a throwable or a screenshot
        // test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }



}


