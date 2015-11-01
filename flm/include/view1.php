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

}
?>