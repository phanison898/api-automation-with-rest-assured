package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.reports.ExtentManager;
import com.reports.ExtentTestManager;
import com.utils.LoggerUtil;

public class TestListener implements ITestListener {

	private static LoggerUtil logger = new LoggerUtil();

	private static ThreadLocal<String> testCaseName = new ThreadLocal<String>();

	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {

		testCaseName.set(format(result.getMethod().getMethodName()));

		ExtentTestManager.createTest(testCaseName.get());
		ExtentTestManager.getTest().assignCategory(result.getInstance().getClass().getSimpleName());

		logger.start(String.format("Test case [ %s ] Started", testCaseName.get()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.pass(String.format("Test case [ %s ] Passed", testCaseName.get()));
		logger.end();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String error = result.getThrowable().getLocalizedMessage();
		logger.fail(String.format("Test case [ %s ] Failed", testCaseName.get()));
		logger.fail("CAUSE : " + error);
		logger.end();

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.skip(String.format("Test case [ %s ] Skipped", testCaseName.get()));
		logger.end();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.flushExtentReport();
	}

	// Formats camel word to English sentence by including spaces before upper case
	private static String format(String input) {

		StringBuilder output = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {

			char c = input.charAt(i);

			if (i == 0) {
				if (Character.isLowerCase(c)) {
					c = Character.toUpperCase(c);
				}
			} else {
				if (Character.isUpperCase(c)) {
					output.append(" ");
					c = Character.toLowerCase(c);
				}
			}

			output.append(c);
		}

		return output.toString()
				.replace("post", "POST")
				.replace("create", "CREATE")
				.replace("get", "GET")
				.replace("read", "READ")
				.replace("put", "PUT")
				.replace("update", "UPDATE")
				.replace("patch", "PATCH")
				.replace("delete", "DELETE");
	}
}
