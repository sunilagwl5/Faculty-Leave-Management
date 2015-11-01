<!DOCTYPE html>

<?php
	include("include/controller.php");
	include("include/view.php");
	$control=new Controller();
	$view=new view();
	$msg=$view->getcollege();
     $fromdean=$msg[1];
	 $todirector=$msg[2];
	 
	 $msg=$view->getdeanpermission();
	 
   if(!empty($_POST['rejected']))
   {
    //Do all the submission part or storing in DB work and all here
	header('Location: reasond.php');
   }
   if(!empty($_POST['Accept']))
   {
		$message = $_POST['textarea'];
		$temp =$_POST['hello'];
		$row=$view->getuser($temp);
		$subject = "Mail from Dean in regard of leave '".$row[0]."'";
		$control->responcedean($row[0]);
		//echo "<script>alert('".$row[0]."')</script>";//echo $message;
		$headers = "From:" . $fromdean;
		mail($todirector,$subject,"Dear Sir,\n\t.'$message'.\n\t please http://designghar.com/flm/directorresponce.php to for give your responce",$headers);
		
	   
    //Do all the submission part or storing in DB work and all here
	//select Content,From_date,To_date from leave_status where Username="sunilagwl5"; 
	//select Username from leave_status where Hod="";
	//select Username from leave_status where Dean="";
	//select Username from leave_status where Director="";
	//update leave_status set Hod='Rejected',Dean='Rejected',Director='Rejected' where Username="sunilagwl5" ;
	//SELECT DATEDIFF(To_date,From_date) FROM leave_status where Username='sunilagwl5';
	//select Hod,Dean,Director from college where Name='The LNM Institue of Information Technology';
   }
?>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<title>Dean Responce</title>
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
		<form action="deanresponce.php" method ="post" id=form>
			<h1>Dean Responce</h1>
			<br></br>
			<div>
				<input type="text" placeholder="<?php echo $fromdean ?> " id="username" />
			</div>
			<div>
				<input type="password" placeholder="<?php echo $todirector ?>" id="username" />
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
			<br></br>
			<div>
					<textarea name="textarea" id='txtArea' placeholder='Text Area' rows='10' cols='40'></textarea>			
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