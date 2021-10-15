package poseidon.mod.objects.block.particlerods;

class ParticleCZ 
{
	double resultx, resulty, resultz;

	public ParticleCZ()
	{
		
	}
	
	public ParticleCZ(double zStep, double zA, double zw, double za, double zh, double zl, int z) 
	{		
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
				resultz = (1*zh)/(zStep*TileEntityParticleRod.s) + za*TileEntityParticleRod.s + zl; 
				break;
			case 7:
				resultz = zStep; 
				break;
			case 8:
				resultz = 0;
				break;
		}
		
		//DEBUG ONLY
		//System.out.println("(xstep, xA, xw, xa, step, x): (" + xStep + ", " + xA + ", " + xw + ", " + xa + ", " + TileEntityParticleRod.s + ", " + getResultX());
		//System.out.println("(ystep, yA, yw, ya, step, y): (" + yStep + ", " + yA + ", " + yw + ", " + ya + ", " + TileEntityParticleRod.s + ", " + getResultY());
		//System.out.println("(zstep, zA, zw, za, step, z): (" + zStep + ", " + zA + ", " + zw + ", " + za + ", " + TileEntityParticleRod.s + ", " + getResultZ());
	}

	double getResultZ() { return resultz; }
}