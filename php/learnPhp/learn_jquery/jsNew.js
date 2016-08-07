/*
 * This is a JavaScript Scratchpad.
 *
 * Enter some JavaScript, then Right Click or choose from the Execute Menu:
 * 1. Run to evaluate the selected text (Ctrl+R),
 * 2. Inspect to bring up an Object Inspector on the result (Ctrl+I), or,
 * 3. Display to insert the result in a comment after the selection. (Ctrl+L)
 */

function Person(name, age) {     
    this.getName = function() { return name; };     
    this.setName = function(newName) { name = newName; };     
    this.getAge = function() { return age; };     
    this.setAge = function(newAge) { age = newAge; };     
}     
     
var p1 = new Person("sdcyst",3);     
document.writeln(p1.getName());  //sdcyst     
document.writeln(p1.name);       //undefined    因为Person('类')没有name属性     
p1.name = "mypara"    //显示的给p1添加name属性     
document.writeln(p1.getName());  //sdcyst      但是并不会改变getName方法的返回值     
document.writeln(p1.name);       //mypara      显示出p1对象的name属性     
p1.setName("sss");    //改变私有的"name"属性   
document.writeln(p1.getName());  //sss     
document.writeln(p1.name);       //仍旧为mypara 
