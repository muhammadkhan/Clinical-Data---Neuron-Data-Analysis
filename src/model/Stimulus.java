package model;

public enum Stimulus {
	N,Q,H,S,NQ,NH,NS,HQ,HS,SQ;
	
	public String toString(){
		switch(this){
		case N: return "N";
		case Q: return "Q";
		case H: return "H";
		case S: return "S";
		case NQ: return "NQ";
		case NH: return "NH";
		case NS: return "NS";
		case HQ: return "HQ";
		case HS: return "HS";
		case SQ: return "SQ";
		default: return null;
		}
	}
	
	public static Stimulus stimulus_of_string(String s){
		if(s.equals("N"))
			return N;
		else if(s.equals("Q"))
			return Q;
		else if(s.equals("H"))
			return H;
		else if(s.equals("S"))
			return S;
		else if(s.equals("NQ"))
			return NQ;
		else if(s.equals("NH"))
			return NH;
		else if(s.equals("NS"))
			return NS;
		else if(s.equals("HQ"))
			return HQ;
		else if(s.equals("HS"))
			return HS;
		else if(s.equals("SQ"))
			return SQ;
		else
			return null;
	}
}
