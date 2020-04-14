package com.example.mentaltraining

data class DisplayListItem(var name : String,
                           var subList : Array<DisplayListItem>,
                           val siblings : Array<DisplayListItem>,
                           val hasActivity: Boolean = false) : Comparable<DisplayListItem> {


    private var mParent = this      //parent of a display item so we can backtrack
    private var mSiblings : Array<DisplayListItem> = arrayOf()      //siblings to help with backtracking
    private var mSubList : Array<DisplayListItem> = arrayOf()

    //public variable that says whether item should go to new activity or not. Activity type should b parsed out in MainActivity.kt
    var mHasActivity : Boolean = false

    //this is for full constructor
    init{
        //sets every subList elements' parent to this
        for(i in subList){
            i.setParent(this)
        }

        //set member variables
        mSiblings = siblings
        mSubList = subList
        mHasActivity = hasActivity
    }
    //partial constructors
    constructor() : this ("", arrayOf(), arrayOf())    //empty constructor
    constructor(name : String) : this(name, arrayOf<DisplayListItem>(), arrayOf<DisplayListItem>())  //constructor for display items with no children(last layer of lists)
    constructor(name : String, subList : Array<DisplayListItem>) : this(name, subList, arrayOf<DisplayListItem>())
    constructor(name : String, activity : Boolean) : this(name, arrayOf<DisplayListItem>(), arrayOf<DisplayListItem>(), activity)
    constructor(name : String, subList : DisplayListItem) : this(name, arrayOf(subList), arrayOf<DisplayListItem>())

    override fun compareTo(other : DisplayListItem) : Int = this.name.compareTo(other.name)
    override fun toString() : String = "$name"

    fun getAllSiblings() : Array<DisplayListItem> = mSiblings
    fun getParent() : DisplayListItem = mParent

    fun setParent(parent : DisplayListItem){
        mParent = parent
    }

    fun setSiblings(s : Array<DisplayListItem>) {
        mSiblings = s
    }

    fun getSiblingsAndSelf() : Array<DisplayListItem>{
        var result : ArrayList<DisplayListItem> = arrayListOf()
        for(i in this.mSiblings){
            result.add(i)
        }
        result.add(this)
        return result.toTypedArray()
    }

    /*
        Recursively finds Main Category (top level) of a Display List Item
     */
    fun getMainCategoryName() : String {
        if(this.getParent().name == this.name){
            return this.name
        }
        return this.getParent().getMainCategoryName()
    }

    fun getMainCategoryItem() : DisplayListItem {
        if(this.getParent().name == "null"){
            return this.getParent()
        }
        else{
            this.getMainCategoryItem()
        }
        return DisplayListItem()
    }

}