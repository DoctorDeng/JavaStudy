var calculator2 = new Object();
calculator2.add = function (n1, n2) {return n1 + n2;}
calculator2.subtract = function (n1, n2) { return n1 - n2};
calculator2.multiply = function (n1, n2){ return n1 * n2};
calculator2.divide = function (n1, n2) { return n1 / n2};

function testArray(obj) {
    var IntArray = Java.type("int[]");
    var array = new IntArray(5);
    array[0] = 5;
    array[1] = 4;
    array[2] = 3;
    array[3] = 2;
    array[4] = 1;
    print(obj.get("aaa"));
    //return obj.get("aaa");
}