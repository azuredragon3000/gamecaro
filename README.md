# gamecaro

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class getContentALMweb extends excel_xlsx {
	
	private static String baseExcel = "D:\\WORKING\\MainWorkspace\\JAVA\\LIBRARY\\report_alm.xlsx";
	//private static String targetExcel = "D:\\WORKING\\MainWorkspace\\JAVA\\LIBRARY\\report_alm_output.xlsx";
	
	getContentALMweb(String file) throws FileNotFoundException, IOException {
		super(file);
		webcontent = new ArrayList<workitem>();
	}

	private ArrayList<workitem> webcontent;
	
	

	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		getContentALMweb scrap = new getContentALMweb(baseExcel);
		
		/* define driver: firefox */
		System.setProperty("webdriver.gecko.driver", "D:\\WORKING\\MainWorkspace\\JAVA\\LIBRARY\\geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().window().setPosition(new Point(-20000, 0));
		
		/* get all WIs from ALM */
		ArrayList<String> WIs = getallWI("https://rb-alm-28-p.de.bosch.com/ccm/quickplanner/cp#items:planType=mywork&paneType=paneTypeAll");
		
		/* get content each WIs */
	    for(int t=0;t<WIs.size();t++)
	    {
	    	processWI(WIs.get(t),scrap.webcontent);
	    }
	    
	    /* for testing */
	    //test(WIs.get(0),scrap.webcontent);
	  
	    /* print web content to excel */
	    print_excel(scrap.webcontent);
	}
	
	@SuppressWarnings("unused")
	private static void test(String WI, ArrayList<workitem> webcontent) {
		processWI(WI,webcontent);
	    System.out.println(webcontent.size());
	    for(int i=0;i<webcontent.size();i++){
	    	System.out.println(webcontent.get(0).des);
	    	System.out.println(webcontent.get(0).estimate);
	    	System.out.println(webcontent.get(0).filed_against);
	    	System.out.println(webcontent.get(0).nametask);
	    	System.out.println(webcontent.get(0).parent_link);
	    	System.out.println(webcontent.get(0).progress);
	    	System.out.println(webcontent.get(0).spent);
	    	System.out.println(webcontent.get(0).start_date);
	    	System.out.println(webcontent.get(0).type_task);
	    	System.out.println(webcontent.get(0).wi);
	    }
	}


	private static void print_excel(ArrayList<workitem> webcontent) throws FileNotFoundException, IOException {
		
		
		/* get base excel */
		//excel_xlsx ex = new excel_xlsx(baseExcel,"report",false);
		/* print to target excel*/	
		//ex.write_to_almweb_order(webcontent ,targetExcel);
	}

	private static ArrayList<workitem> processWI(String web,ArrayList<workitem> webcontent) {
		
		WebDriver driver = new FirefoxDriver();
		workitem iwebcontent = new workitem();
		driver.manage().window().setPosition(new Point(-20000, 0));
	    driver.get(web);
	    
	    WebElement element;
	    
	    try{
	    	/* print name webcontent */
		    element = driver.findElement(By.xpath("//div[@class='SummaryFieldInner']"));
		    iwebcontent.nametask = element.getText();
	    }catch(NoSuchElementException e1){
	    	iwebcontent.nametask = "";
	    }
	    
	    try{
	    	/* print number WI */
		    element = driver.findElement(By.xpath("//div[@class='RichTextEditorWidget ViewBorder com-ibm-team-workitem-shared-RichText cke_editable cke_editable_inline cke_contents_ltr']"));
		    iwebcontent.des = element.getText();
	    }catch(NoSuchElementException e1){
	    	iwebcontent.des = "";
	    }
	    
	    
	    try{
	    	/* print number WI */
		    element = driver.findElement(By.xpath("//span[@class='TitleText']"));
		    iwebcontent.wi = element.getText();
	    }catch(NoSuchElementException e1){
	    	iwebcontent.wi = "";
	    }
	    
	    try{
	    	/* print effort estimation */
		    element = driver.findElement(By.xpath("//div[@class='EstimateWidget2']"));
		    iwebcontent.estimate = element.getText();
	    }catch(NoSuchElementException e1){
	    	iwebcontent.estimate ="";
	    }
	    
	    try{
	    	/* print effort actual */
		    element = driver.findElement(By.xpath("//div[@id='com_ibm_team_apt_web_ui_internal_parts_DurationWidget_2']"));
		    iwebcontent.spent = element.getText();	
	    }catch(NoSuchElementException e1){
	    	iwebcontent.spent = "";
	    }
	    
	    
	    try{
	    	/* print start date */
		    element = driver.findElement(By.xpath("//label[@class='TimeLabel']"));
		    iwebcontent.start_date = element.getText();	
	    }catch(NoSuchElementException e1){
	    	iwebcontent.start_date ="";	
	    }
	    
	    
	    try{
	    	/* print field against */
		    element = driver.findElement(By.xpath("//div[@id='com_ibm_team_workitem_web_mvvm_view_ComboWithButtonView_0']"));
		    iwebcontent.filed_against = element.getText();	
	    }catch(NoSuchElementException e1){
	    	iwebcontent.filed_against ="";
	    }
	    
	    try{
	    	/* print type task */
		    element = driver.findElement(By.xpath("//div[@id='com_ibm_team_workitem_web_mvvm_view_queryable_combo_QueryableComboView_0']"));
		    iwebcontent.type_task = element.getText();	
	    }catch(NoSuchElementException e1){
	    	iwebcontent.type_task = "";
	    }
	    
	    try{
	    	/* print progress task */
		    element = driver.findElement(By.xpath("//div[@class='fieldWrapper']"));
		    iwebcontent.progress = element.getText();	
	    }catch(NoSuchElementException e1){
	    	iwebcontent.progress ="";
	    }  
	    
	    /* click to tab links */
	    if(driver.findElements(By.xpath("//a[@title='Links']")).size() != 0)
	    {
	    	try{
	    		driver.findElement(By.xpath("//a[@title='Links']")).click();
		    	element = driver.findElement(By.xpath("//div[@id='com_ibm_team_rtc_foundation_web_ui_views_ArtifactListView_1']"));
			    iwebcontent.parent_link = element.getText();	
		    }catch(NoSuchElementException e1){
		    	iwebcontent.parent_link ="";
		    }
	    }
	    
	    /* click to tab time tracking */
	    if(driver.findElements(By.xpath("//a[@title='Time Tracking']")).size() != 0)
	    { 
	    	
		    try{
		    	driver.findElement(By.xpath("//a[@title='Time Tracking']")).click();	    
			    List<WebElement> list_element = driver.findElements(By.xpath("//a[@href='#']"));
			    for(int i=0;i<list_element.size();i++)
			    {
			    	if(list_element.get(i).getAttribute("title").contains("2020"))
			    	{ 		
			    		String[] day = new String[7];
			    		day = convert(list_element.get(i).getAttribute("title"));
			    		list_element.get(i).click();
			    		element = driver.findElement(By.xpath("//table[@class='tptTable tptTSTable']"));
			    		List<WebElement> input1 = element.findElements(By.xpath(".//th"));
			    		List<WebElement> input2 = element.findElements(By.xpath(".//td"));
			    		int num=0;
			    		for(int i1=0;i1<input1.size();i1++)
			    		{
			    			if(((input1.get(i1).getText().equals("Owner")))||((input1.get(i1).getText().equals("Time Code")))
			    					||((input1.get(i1).getText().equals("Week Total")))||((input1.get(i1).getText().equals("Work Item Total")))
			    					||((input1.get(i1).getText().equals("Actions"))))
			    			{
			    				 
			    			}else{
			    				iwebcontent.date.add(input1.get(i1).getText()+"-"+input2.get(i1).getText()+day[num++]);
			    			}
			    		}
			    	}
			    }
		    }catch(NoSuchElementException e1){
		    	
		    }
	    	
		    
	    }else
	    {
	    	System.out.println("not exist");
	    }
	    
	    webcontent.add(iwebcontent);
		driver.close();
		return webcontent;
	}


	private static String[] convert(String st) {
		String ret[] = new String[7];
		String month = st.split(" ")[0];
		String year = st.split(" ")[2];
		String date1 = st.split(",")[0].split("\\s+")[1];
		ret[0] = date1 + "-"+month+"-"+year;
		ret[1] = String.valueOf(Integer.valueOf(date1)+1)+ "-"+month+"-"+year;
		ret[2] = String.valueOf(Integer.valueOf(date1)+2)+ "-"+month+"-"+year;
		ret[3] = String.valueOf(Integer.valueOf(date1)+3)+ "-"+month+"-"+year;
		ret[4] = String.valueOf(Integer.valueOf(date1)+4)+ "-"+month+"-"+year;
		ret[5] = String.valueOf(Integer.valueOf(date1)+5)+ "-"+month+"-"+year;
		ret[6] = String.valueOf(Integer.valueOf(date1)+6)+ "-"+month+"-"+year;
		return ret;
	}


	public static ArrayList<String> getallWI(String web) 
	{    
		ArrayList<String> ret = new ArrayList<String>();
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().setPosition(new Point(-20000, 0));
	    driver.get(web);
	 
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemsContainer")));
	    
	    List<WebElement> element = driver.findElements(By.xpath("//*[@class='list-group-item workItem']"));;
	    
	    for(int i=0;i<element.size();i++)
	    {
	    	ret.add(element.get(i).findElement(By.cssSelector("a")).getAttribute("href"));
	    }
	    driver.close();
	    return ret;
	}
}

class workitem
{
	String nametask;
	String wi;
	String estimate;
	String spent;
	String start_date;
	String filed_against;
	String parent_link;
	String type_task;
	String progress;
	String des;
	ArrayList<String> date;
	workitem()
	{
		start_date = "";
		filed_against = "";
		nametask = "";
		wi = "";
		parent_link = "";
		estimate="";
		spent = "";
		type_task="";
		progress = "";
		des = "";
		date = new ArrayList<String>();
	}
}
