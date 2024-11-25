ta(tanner,csc460).
ta(aayush,csc460).
ta(aayush,csc445).

instructor(zhang,csc460). 
instructor(mccann,csc445).

supervises(X,Y) :- instructor(X,Z), ta(Y,Z).

