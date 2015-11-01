<?php
/**
 * Created by PhpStorm.
 * User: Anuvakah
 * Date: 4/1/14
 * Time: 1:27 AM
 */

class Controller {

	function Controller()
	{
		 $this->connection=mysqli_connect("mysql.serversfree.com","u219477436_su","hello1993");
		if (mysqli_connect_errno())
  		{
  			echo "Failed to connect to MySQL: " . mysqli_connect_error();
  		}		
  		mysqli_select_db($this->connection,"u219477436_flm");
	}

	/***  Add New Student Details ****/
	function login($control,$userid, $password)
	{
		
		$auth=$this->authenticate($control,$userid,$password);
		if($auth==0)
			{
				$r["re"]="Wrong Username or password";
                print(json_encode($r));
            }
			else
			{
				$r["re"]="User Found";
                print(json_encode($r));
			}
	}
	function authenticate($control,$userid,$password)
	{
		$query="SELECT * FROM user WHERE Username='$userid'";
		$result = mysqli_query($this->connection,$query);
		if($result == FALSE) 
		{
			return 0;
			die(mysql_error()); // TODO: better error handling
		}
			
		if(!($row = mysqli_fetch_array($result)))
		{
			//echo "<script>alert('Database error')</script>";
			return 0;
		}
		if(!($userid==$row[1]&&$password==$row[2]))
		{
			//echo "<script>alert('Username or Password do not match')</script>";
			return 0;
		}
		return 1;
	}
	function addcustomerDetails($options)
	{
		$query="INSERT INTO custominfo(Email_id, Name,Phone_no,Street_addr,City,State,pin,Password) 
			VALUES ('$options[0]' ,'$options[1]' ,'$options[2]' ,'$options[3]' ,'$options[4]' ,'$options[5]' ,'$options[6]' ,'$options[7]')";
		
		$result = mysqli_query($this->connection,$query);
		
		if(!$result)
			echo "Databse Error";
		else
			echo "<script>alert('Added to database Now login to your account')</script>";
		
		header('Location: Login.php');
	}

	function leave_creation($username,$type,$content)
	{
		$query="INSERT INTO leave_status (Username, Type_leave,Content) VALUES ('$username','$type','$content')";
		
		$result = mysqli_query($this->connection,$query);
		if(!$result)
			{
				$r["re"]="Wrong Username or password";
                print(json_encode($r));
            }
			else
			{
				$r["re"]="User Found";
                print(json_encode($r));
			}
		
	}
	
	function responcehod($username)
	{
		$query="update leave_status set Hod='Accepted' where Username='".$username."'";
		
		$result = mysqli_query($this->connection,$query);
		
		if(!$result)
			echo "Databse Error";
		else
			echo "<script>alert('You accepted leave and forwarded it to dean')</script>";
	}
	function responcedean($username)
	{
		$query="update leave_status set Dean='Accepted' where Username='".$username."'";
		
		$result = mysqli_query($this->connection,$query);
		
		if(!$result)
			echo "Databse Error";
		else
			echo "<script>alert('You accepted leave and forwarded it to director')</script>";
	}
	function responcedirector($username)
	{
		$query="update leave_status set Director='Accepted' where Username='".$username."'";
		
		$result = mysqli_query($this->connection,$query);
		
		if(!$result)
			echo "Databse Error";
		else
			echo "<script>alert('Leave is senctioned')</script>";
	}
	function rejectection($username)
	{
		$query="update leave_status set Hod='Rejected',Dean='Rejected',Director='Rejected' where Username='".$username."'";
		
		$result = mysqli_query($this->connection,$query);
		
		if(!$result)
			echo "Databse Error";
		else
			echo "<script>alert('You rejected leave')</script>";
	}
	function signout()
	{
		session_unset();
		session_destroy();
	}

	
	function correctInput($control,$options)
	{
		$options=stripslashes($options);
		$options = mysqli_real_escape_string($this->connection,$options);
		return $options;
	}

} 