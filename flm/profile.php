<?php
 $myusername=$mypassword="";
  include("include/controller.php");
  include("include/view.php");
  
  $msg="";
  $view=new View();
  $control=new Controller();
  
  $check="";
  //$control=new Controller();
  $username=$control->correctInput($control,$_POST['username']);
  //$username="sunilagwl5@gmail.com";
  $msg=$view->getprofile($username);
  while($row = mysqli_fetch_array($msg))
            {
  
                  $r[]=$row;
                  $check=$row['Name'];
                 // print(json_encode($r));
           

             }
  //$msg=$view->getprofile($username);
	//$check=$msg[1];
	
			//echo "<script>alert('$msg[0]')</script>";
			 if($check==NULL)
			{            
                      $r["re"]="Record is not available";
                      print(json_encode($r));
                 
            }
            else
            {
                 $r["re"]="success";
                 print(json_encode($r));
                // die('Could not connect: ' . mysql_error());
			} 
?>