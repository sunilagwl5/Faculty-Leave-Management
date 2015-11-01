<!DOCTYPE html>

<?php
	include("include/controller.php");
	include("include/view.php");
	$control=new Controller();
	$view=new View(); 
	$msg=$view->getdirectorpermission();
   if(!empty($_POST['rejected']))
   {
    //Do all the submission part or storing in DB work and all here
	header('Location: reasondir.php');
   }
   if(!empty($_POST['Accept']))
   {
		$temp =$_POST['hello'];
		//echo "<script>alert('$temp')</script>";
		$row=$view->getuser($temp);
		$control->responcedirector($row[0]);
    //Do all the submission part or storing in DB work and all here
   }
?>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<title>Director Responce</title>
<link rel="stylesheet" type="text/css" href="css\form.css" />
</head>
<body>
<div class="container">
	<section id="content">
		<form action="" method ="post">
			<h1>Director Responce</h1>
			<div>
				<?php
							echo "<select name ='hello' id='select1' onchange='changeTest(this)'>";
							echo "<option value=''  >Choose the user </option>";
							while($row = mysqli_fetch_array($msg))
							{
								echo "<option value='".$row[1]."'  >".$row[0]." </option>";
							}
							echo "</select>";
					?>
						
			</div>
			<div>
				<input type="submit" name="Accept" value="Forward" />
				<input type="submit" name= "rejected" value="Rejected" />
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div><!-- container -->
</body>
</html>