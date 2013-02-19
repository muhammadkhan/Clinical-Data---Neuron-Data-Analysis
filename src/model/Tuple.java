package model;

public class Tuple<S,T,U>{
		S first;
		T second;
		U third;
		
		public Tuple(S s, T t, U u){
			first = s;
			second = t;
			third = u;
		}
		
		public S getFirst(){ return first;}
		public T getSecond(){ return second; }
		public U getThird(){ return third; }
		
		public void setFirst(S s){ first = s; }
		public void setSecond(T t){ second = t; }
		public void setThird(U u){ third = u; }
	}