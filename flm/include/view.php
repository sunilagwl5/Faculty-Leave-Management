<?php
Class View
{
	function View()
	{
		 $this->connection=mysqli_connect("mysql.serversfree.com","u219477436_su","hello1993");
		if (mysqli_connect_errno())
  		{
  			echo "Failed to connect to MySQL: " . mysqli_connect_error();
  		}		
  		mysqli_select_db($this->connection,"u219477436_flm");
	}
	function getprofile($username)//Get course List of faculty
	{
		$query="select u.Name,c.Name,u.leave1,u.leave2,u.leave3,u.leave4,u.leave5 from user u,college c where u.Username='$username' and u.College_id=c.College_id";
		$result = mysqli_query($this->connection,$query);
		return $result;	
	}
	function getstatus($username)//Get course List of faculty
	{
		$query="SELECT *  FROM leave_status WHERE Username='$username'";
		$result = mysqli_query($this->connection,$query);
		return $result;
		
	}
	function getarea($type,$face)//Get course List of faculty
	{
		$query="SELECT distinct area FROM test_image WHERE type='$type' and facing='$face'";
		$result = mysqli_query($this->connection,$query);
		return $result;
		
	}
	function getpic($type,$face,$area)//Get course List of faculty
	{
		$query="SELECT image FROM test_image WHERE name='".$type."-".$face."-".$area."'";
		$result=mysqli_query($this->connection,$query );
		return $result;
    }
	function getcollege()//Get course List of faculty
	{
		$query="select Hod,Dean,Director from college where Name='The LNM Institue of Information Technology'";
		$result=mysqli_query($this->connection,$query );
		if(!($row = mysqli_fetch_array($result)))
			echo "<script>alert('Databse Error')</script>";
		else
			return $row; 
		//return $result;
    }
	function getuser($con)//Get course List of faculty
	{
		$query="select Username from leave_status where Content='".$con."'";
		$result=mysqli_query($this->connection,$query );
		if(!($row = mysqli_fetch_array($result)))
			echo "<script>alert('Databse Error 123')</script>";
		else
			return $row;
		//return $result;
    	}
	
	function gethodpermission()//Get course List of faculty
	{
		$query="select Username,Content from leave_status where Hod=''";
		$result=mysqli_query($this->connection,$query );
		return $result;
    }
	function getdeanpermission()//Get course List of faculty
	{
		$query="select Username,Content from leave_status where Dean=''";
		$result=mysqli_query($this->connection,$query );
		return $result;
    }
	function getdirectorpermission()//Get course List of faculty
	{
		$query="select Username,Content from leave_status where Director=''";
		$result=mysqli_query($this->connection,$query );
		return $result;
    }
	function getapplicationcontent($user)//Get course List of faculty
	{
		$query="select Content from leave_status where Username='".$user."'";
		$result=mysqli_query($this->connection,$query );
		if(!($row = mysqli_fetch_array($result)))
			echo "<script>alert('Databse Error')</script>";
		else
			return $row; 
		//return $result;
    }		
}
?>