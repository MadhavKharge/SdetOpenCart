package utilities;

import java.awt.Desktop;
import java.io.File;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext testContext) {
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date date
		 * = new Date(); String currentdatetimestamp = df.format(date);
		 * 
		 * Stored in single variable timeStamp
		 */
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter("./reports/" + repName);

		sparkReporter.config().setDocumentTitle("Opencart Automation Report");// Title of report
		sparkReporter.config().setReportName("Opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-module", "customer");
		extent.setSystemInfo("user Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		// capture details through xml file
		String os = testContext.getCurrentXmlTest().getParameter("os"); // runtime/real os
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");// runtime/real browser
		extent.setSystemInfo("Browser", browser);

		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();// capture all the groups from
																							// xml file
		// if groups are/not present then create groups
		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());

		}

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display group in result
		test.log(Status.PASS, result.getName() + "got sucessfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); // to display testclassname in result
		test.assignCategory(result.getMethod().getGroups()); // assign=group

		test.log(Status.FAIL, result.getName() + "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, result.getName() + "Got skiped");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext context) {

		extent.flush();
		
        //automatically opens the report on browser
		
		String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		try {
			URL url=new URL("file:///" + System.getProperty("user.dir") + "/reports" + repName);
			//create the email message
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smpt.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("madhav.kharge2010@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("madhav.kharge2010@gmail.com"); // sender 
			email.setSubject("Test Results");
			email.setMsg("Please Find Attached Report...");
			email.addTo("madhavkharge22@gmail.com");//receiver
			email.attach(url,"extend report","please check report");
			email.send(); //send the email
		
		
		}
			catch(Exception e) {
				e.printStackTrace();
			}
		*/
	}

}
