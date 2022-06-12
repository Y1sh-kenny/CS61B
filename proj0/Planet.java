class Planet
{

	public double xxPos;
	public double yyPos;//current x position
	public double xxVel;//current velocity
	public double yyVel;
	public double mass;
	public String imgFileName;//The name of that corresponds to the image that depicts the body
	double G = 6.67e-11;

	//constructer
	public Planet(double xP,double yP,double xV,double yV,double m,String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	//copy constructer
	public Planet(Planet b)
	{
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	
	//计算两个行星之间的距离的函数
	public double calcDistance(Planet p)
	{
		return java.lang.Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + 
			(this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
	}

	//计算两个行星之间的力的大小
	public double calcForceExertedBy(Planet p)
	{
		return G * mass * p.mass / (calcDistance(p) * calcDistance(p)); 
	}

	public double calcForceExertedByX(Planet p)
	{
		return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
	}
	public double calcForceExertedByY(Planet p)
	{
		return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
	}
	
	public double calcNetForceExertedByX(Planet[] Planet_Array)
	{
		int i = 0;
		double NetForced = 0.0;
		for(i = 0;i<Planet_Array.length;i++)
		{
			if(this.equals(Planet_Array[i]))
				continue;
			NetForced += this.calcForceExertedByX(Planet_Array[i]);
		}
		return NetForced;
	}
	public double calcNetForceExertedByY(Planet[] Planet_Array)
	{
		int i = 0;
		double NetForced = 0.0;
		for(i = 0;i<Planet_Array.length;i++)
		{
			if(this.equals(Planet_Array[i]))
				continue;
			NetForced += this.calcForceExertedByY(Planet_Array[i]);
		}
		return NetForced;
	}

	public void update(double time,double fX,double fY)
	{
		//calc the acceleration
		double aX = fX / this.mass;
		double aY = fY / this.mass;

		this.xxVel = this.xxVel + aX * time;
		this.yyVel = this.yyVel + aY * time;

		this.xxPos = this.xxPos + xxVel * time;
		this.yyPos = this.yyPos + yyVel * time;

	}
	public void draw()
	{
		StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
	}

}