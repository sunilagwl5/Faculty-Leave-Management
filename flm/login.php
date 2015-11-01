<?php
 $myusername=$mypassword="";
  include("include/controller.php");
  
  $msg="";
  $control=new Controller();
    $myusername=$control->correctInput($control,$_POST['username']);
    $mypassword=$control->correctInput($control,$_POST['password']);
	if (!(empty($myusername)||empty($mypassword)))
    {
      $msg=$control->login($control,$myusername,$mypassword);
	}
    
?>