package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.reports.ExtentTestManager;

public class LoggerUtil {

	public void start(String message) {

		System.out.println("-------------------------------------------------------------------------------------");
		consoleLog(LogType.Start, message);
	}

	public void end() {
		System.out.println("-------------------------------------------------------------------------------------");
	}

	public void info(String message) {

		ExtentTestManager.getTest().info(message);
		consoleLog(LogType.Info, message);
	}

	public void pass(String message) {

		ExtentTestManager.getTest().pass(message);
		consoleLog(LogType.Pass, message);
	}

	public void fail(String message) {

		ExtentTestManager.getTest().fail(message);
		consoleLog(LogType.Fail, message);
	}

	public void skip(String message) {

		ExtentTestManager.getTest().skip(message);
		consoleLog(LogType.Skip, message);
	}

	public void warning(String message) {

		ExtentTestManager.getTest().warning(message);
		consoleLog(LogType.Warning, message);
	}

	public void createJsonCodeBlock(String message, String json) {
		info(message);
		ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
	}

	public void createJsonCodeBlocks(String json1, String json2) {
		ExtentTestManager.getTest().info(MarkupHelper.createCodeBlocks(new String[] { json1, json2 }));
	}

	private void consoleLog(String logType, String message) {

		SimpleDateFormat format = new SimpleDateFormat("[hh:mm:ss]");
		Date date = new Date();
		System.out.println(format.format(date) + "[" + logType + "] " + message);
	}

}
