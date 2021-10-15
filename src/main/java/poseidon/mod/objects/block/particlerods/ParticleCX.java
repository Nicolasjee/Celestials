package poseidon.mod.objects.block.particlerods;

class ParticleCX 
{
	double resultx, resulty, resultz;

	public ParticleCX()
	{
		
	}
	
	public ParticleCX(double xStep, double xA, double xw, double xa, double xh, double xl, int x)
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
				resultx = (1*xh)/(xStep*TileEntityParticleRod.s) + xa*TileEntityParticleRod.s + xl; 
				break;
			case 7:
				resultx = xStep; 
				break;
			case 8:
				resultx = 0;
				break;
		}
	}
	
	double getResultX() //throws BadParticle
	{ 
		return resultx; 
	}
}