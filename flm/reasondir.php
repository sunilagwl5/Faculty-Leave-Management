<!DOCTYPE html>.

<?php
	include("include/controller.php");
	include("include/view.php");
	$control=new Controller();
	$view=new view();
	$msg=$view->getcollege();
	$fromhod=$msg[0];
	$todean=$msg[1];
	 
	$msg=$view->getdirectorpermission();
    
   if(!empty($_POST['Send']))
   {
		$temp =$_POST['hello'];
		$row=$view->getuser($temp);
		$control->rejectection($row[0]);
	   //Do all the submission part or storing in DB work and all here
   }
?>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<title>Reason</title>
<link rel="stylesheet" type="text/css" href="css\form.css" />
</head>
<body>
<div class="container">
	<section id="content">
		<form action="" method="post">
			<h1>Reason</h1>
			<div>
				<?php
							echo "<select name ='hello' id='select1' >";
							echo "<option value=''  >Choose the user </option>";
							while($row = mysqli_fetch_array($msg))
							{
								echo "<option value='".$row[1]."'  >".$row[0]." </option>";
							}
							echo "</select>";
					?>
			</div>
			<div>
				<textarea id="txtArea" placeholder="Text Area" rows="10" cols="40"></textarea>
			</div>
			<div>
				<input type="submit" name="Send" value="Submit" />
				
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div><!-- container -->
</body>
</html>