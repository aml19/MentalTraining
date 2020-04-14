package com.example.mentaltraining

import android.util.Xml
import org.xmlpull.v1.XmlSerializer
import java.io.StringWriter
import java.lang.RuntimeException

class XMLParser {

    fun writeDisplayItemsToXmlFile(displayListItems : Array<DisplayListItem>) {
        var serializer : XmlSerializer = Xml.newSerializer()
        var writer : StringWriter = StringWriter()
        try {
            serializer.setOutput(writer)
            serializer.startDocument("UTF-8", true)
            serializer.startTag("", "Start")
            //go through MainCategories and write "name" as tags
            for(element in displayListItems){
                //go through sub items until subList is empty
                var sublist = element.subList
                while(sublist.isNotEmpty()){
                    sublist = sublist
                }
            }

            serializer.endTag("", "Start")
            serializer.endDocument()
            val result = writer.toString()
            //write to file


        } catch(e : Exception){
            throw RuntimeException(e)
        }
    }
}