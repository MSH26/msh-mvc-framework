# msh-mvc-framework
It's a web mvc framework with front controller design pattern which I would like to call as "msh-mvc-framework". 
Here is an demo login system built using this framework.

1. How to access the project:
	application should be hit by the url like "msh-framework/mvc/module_name/controller_name"


2. Description:

	a. Here i use the concept of 'module' and 'controller'. In this framework 'action' concept has been 
	depricated. This framework will only work for get request and post request method for other methods 
	global controller must implement those methods.It's simple just copy the whole code of get method of 
	global controller into other methods then it will work properly. Nothing is needed to be change.
	
	b. I have binded the orm model with the framework.
	
3. About mvc.xml file's constrants:
	You must allways follow the tags and it's hierarchy given as bellow.
	<app>
		<mvc>
			<module-name></module-name>
			<controller-name></controller-name>
			<controller-absolute-name></controller-absolute-name>
			<model-name></model-name>
			<model-absolute-name></model-absolute-name>
			<view></view>
		</mvc>
	</app>
	
	*****Remember stricktly "You have to write all the tags mantioned in this hierarchy in your mvc.xml".
	**Remember stricktly "The siblings can be place any orders but parents must be in place like this".
	
4. About IController:
	***You have to declare the method of request type in this interface and it should be in lower case 
	and every methods will have the same parameters(see documentation)
