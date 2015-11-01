<!DOCTYPE html>
<!DOCTYPE html>

<?php
	include("include/controller.php");
	include("include/view.php");
	$control=new Controller();
	$view=new View();
	$msg=$view->getcollege();
     $fromhod=$msg[0];
	 $todean=$msg[1];
	 
	 $msg=$view->gethodpermission();
	
   if(!empty($_POST['rejected']))
   {
    //Do all the submission part or storing in DB work and all here
	header('Location: reasonhod.php');
   }
   if(!empty($_POST['Accept']))
   {
		$message = $_POST['textarea'];
		$temp =$_POST['hello'];
		$row=$view->getuser($temp);
		$subject = "Mail from hod in regard of leave '".$row[0]."'";
		
		$control->responcehod($row[0]);
		//echo "<script>alert('".$subject."')</script>";//echo $message;
		$headers = "From:" . $fromhod;
		//echo "<script>alert('".$row[0]."')</script>";//echo $message;
		mail($todean,$subject,"Dear Sir,\n\t.'$message'.\n\t please http://designghar.com/flm/deanresponce.php to for give your responce",$headers);
		//mail($todean,$subject,$message,$headers);
		//$control->responcehod();
		//echo "Mail Sent.";
    //Do all the submission part or storing in DB work and all here
   }
?>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<title>Hod Responce</title>
<link rel="stylesheet" type="text/css" href="css\form.css" />
<script type="text/javascript">
function changeTest(sel) { 
 var value = sel.value;  
	document.getElementById("txtArea").innerHTML = value;
 }
</script>

</head>
<body>
<div class="container">
	<section id="content">
		<form action="" method ="post">
			<h1>Hod Responce</h1>
			<br></br>
			<div>
				<input type="text" placeholder="<?php echo $fromhod ?> " id="username" />
			</div>
			<div>
				<input type="password" placeholder="<?php echo $todean ?> " id="username" />
			</div>
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
				<textarea name="textarea" id="txtArea" placeholder="Text Area" rows="10" cols="40"></textarea>
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