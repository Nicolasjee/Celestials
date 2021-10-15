package poseidon.mod.objects.block.particlerods;

public class Particle 
{
	double resultx, resulty, resultz;
	
	public Particle(double xStep, double yStep, double zStep, 
					double xA, double xw, double xa, double xh, double xl,
					double yA, double yw, double ya, double yh, double yl,
					double zA, double zw, double za, double zh, double zl,
					int x, int y, int z) 
	{
		switch(x)
		{
			case 0:
				resultx = xA*Math.cos(xw*(xStep*TileEntityParticleRod.s) + xa) + xh*TileEntityParticleRod.s + xl;
				break;
			case 1:
				resultx = xA*Math.sin(xw*(xStep*TileEntityParticleRod.s) + xa) + xh*TileEntityParticleRod.s + xl;
				break;
			case 2:
				resultx = xA*Math.tan(xw*(xStep*TileEntityParticleRod.s) + xa) + xh*TileEntityParticleRod.s + xl;
				break;
			case 3:
				resultx = xStep*TileEntityParticleRod.s + xh*TileEntityParticleRod.s + xl;
				break;
			case 4:
				resultx = Math.pow((double) xStep, TileEntityParticleRod.s) + xh*TileEntityParticleRod.s + xl;
				break;
			case 5:
				resultx = Math.log(xStep*TileEntityParticleRod.s) + xh*TileEntityParticleRod.s + xl;
				break;
			case 6:
				resultx = (1*xh)/(xStep*TileEntityParticleRod.s) + xl; 
				break;
			case 7:
				resultx = 0;
				break;
		}
		
		switch(y)
		{
			case 0:
				resulty = yA*Math.cos(yw*(yStep*TileEntityParticleRod.s) + ya) + yh*TileEntityParticleRod.s + yl;
				break;
			case 1:
				resulty = yA*Math.sin(yw*(yStep*TileEntityParticleRod.s) + ya) + yh*TileEntityParticleRod.s + yl;
				break;
			case 2:
				resulty = yA*Math.tan(yw*(yStep*TileEntityParticleRod.s) + ya) + yh*TileEntityParticleRod.s + yl;
				break;
			case 3:
				resulty = yStep*TileEntityParticleRod.s + yh*TileEntityParticleRod.s + yl;
				break;
			case 4:
				resulty = Math.pow((double) yStep, TileEntityParticleRod.s) + yh*TileEntityParticleRod.s + yl;
				break;
			case 5:
				resulty = Math.log(yStep*TileEntityParticleRod.s) + yh*TileEntityParticleRod.s + yl;
				break;
			case 6:
				resulty = (1*yh)/(yStep*TileEntityParticleRod.s) + yl; 
				break;
			case 7:
				resulty = 0;
				break;
		}
		
		switch(z)
		{
			case 0:
				resultz = zA*Math.cos(zw*(zStep*TileEntityParticleRod.s) + za) + zh*TileEntityParticleRod.s + zl;
				break;
			case 1:
				resultz = zA*Math.sin(zw*(zStep*TileEntityParticleRod.s) + za) + zh*TileEntityParticleRod.s + zl;
				break;
			case 2:
				resultz = zA*Math.tan(zw*(zStep*TileEntityParticleRod.s) + za) + zh*TileEntityParticleRod.s + zl;
				break;
			case 3:
				resultz = zStep*TileEntityParticleRod.s + zh*TileEntityParticleRod.s + zl;
				break;
			case 4:
				resultz = Math.pow((double) zStep, TileEntityParticleRod.s) + zh*TileEntityParticleRod.s + zl;
				break;
			case 5:
				resultz = Math.log(zStep*TileEntityParticleRod.s) + zh*TileEntityParticleRod.s + zl;
				break;
			case 6:
				resultz = (1*zh)/(zStep*TileEntityParticleRod.s) + zl; 
				break;
			case 7:
				resultz = 0;
				break;
		}
		
		//DEBUG ONLY
		//System.out.println("(xstep, xA, xw, xa, step, x): (" + xStep + ", " + xA + ", " + xw + ", " + xa + ", " + TileEntityParticleRod.s + ", " + getResultX());
		//System.out.println("(ystep, yA, yw, ya, step, y): (" + yStep + ", " + yA + ", " + yw + ", " + ya + ", " + TileEntityParticleRod.s + ", " + getResultY());
		//System.out.println("(zstep, zA, zw, za, step, z): (" + zStep + ", " + zA + ", " + zw + ", " + za + ", " + TileEntityParticleRod.s + ", " + getResultZ());
	}
	
	double getResultX() { return resultx; }
	double getResultY() { return resulty; }
	double getResultZ() { return resultz; }
}
