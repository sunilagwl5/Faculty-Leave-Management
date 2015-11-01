<?php
  include("include/controller.php");
  
  $msg="";
  $control=new Controller();
    $username=$control->correctInput($control,$_POST['Username']);
    $type=$control->correctInput($control,$_POST['Type_leave']);
	$content=$control->correctInput($control,$_POST['Content']);
	//$username="sunill@gmail.com";
	//$type="leave1";
	//$content="hellowlromfnjgjgkglg";
	if (!(empty($username)||empty($type)||empty($content)))
    {
      $msg=$control->leave_creation($username,$type,$content);
	}
    
?>