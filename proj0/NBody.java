class NBody{
    public static double readRadius(String filename)
    {
        In in = new In(filename);

        int num = in.readInt();
        double DoubleInFile = in.readDouble();

        return DoubleInFile;
    }

    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);

        int pNum = in.readInt();
        double uSize =in.readDouble();

        Planet pArray[] = new Planet[pNum];
        for(int i = 0;i<pNum;i++)
        {
            double xp = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            pArray[i] = new Planet(xp,yPos,xVel,yVel,mass,imgFileName);
        }

        return pArray;
    }

    public static void main(String[] args) 
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet planets[] = readPlanets(filename);


        StdDraw.setXscale(-r ,r );
        StdDraw.setYscale(-r,r);

        StdDraw.enableDoubleBuffering();

        StdDraw.clear();


        
        double t = 0;
        while(t <= T)
        {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for(int i = 0;i<planets.length;i++)
            {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i =0;i<planets.length;i++)
            {
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0,0,"images/starfield.jpg");

            for(Planet planet : planets)
            {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) 
        {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }

}