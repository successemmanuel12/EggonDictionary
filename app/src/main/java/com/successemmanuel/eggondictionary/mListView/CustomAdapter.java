package com.successemmanuel.eggondictionary.mListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.successemmanuel.eggondictionary.DataObject.DataObject;
import com.successemmanuel.eggondictionary.DefinitionActivity;
import com.successemmanuel.eggondictionary.R;

import java.util.ArrayList;



public class CustomAdapter extends BaseAdapter implements Filterable{


    Context c;
    ArrayList<DataObject> dataObjects;
    ArrayList<DataObject> filterData; // Array to hold the filter data.
    CustomFilter filter;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<DataObject> dataObjects) {
        this.c = c;
        this.dataObjects = dataObjects;
        this.filterData = dataObjects;
    }


    @Override
    public int getCount() {
        return dataObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return dataObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        TextView wordsTxt = (TextView) convertView.findViewById(R.id.wordsTxt);
        //TextView typeTxt = (TextView) convertView.findViewById(R.id.text_type);
        //TextView definitionTxt = (TextView) convertView.findViewById(R.id.text_meaning);

        final String words = dataObjects.get(position).getWord();
        final String type = dataObjects.get(position).getType();
        final String definition = dataObjects.get(position).getDefinition();
        final String example = dataObjects.get(position).getExample();
// BINDING

        wordsTxt.setText(words);
        //typeTxt.setText(type);
        //definitionTxt.setText(definition);

        //final int pos = position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDefinitionActivity(words, type, definition, example);
            }
        });

        return convertView;
    }

    public  void openDefinitionActivity(String word, String type, String definition, String example){
        Intent i = new Intent(c, DefinitionActivity.class);
        i.putExtra("WORD_KEY", word);
        i.putExtra("TYPE_KEY", type);
        i.putExtra("DEFINITION_KEY", definition);
        i.putExtra("EXAMPLE_KEY",example);

        c.startActivity(i);
    }

    @Override
    public Filter getFilter() {
        if (filter==null){
            filter = new CustomFilter(filterData,this);
        }
        return filter;
    }

    /** public void addWord(Context context){
        myDBAdapter = new DBAdapter(context);
        dataObjects = myDBAdapter.getAllWordsList();
        //popilate database with default questions if empty.

        if (dataObjects.isEmpty()){
            myDBAdapter.add("Apple","n:","Apple is a delicious Fruit");
            myDBAdapter.add("Dog", "n:", "is a Domestic animal that lives at home");
        }
        dataObjects = myDBAdapter.getAllWordsList();
    }*/

   class  CustomFilter extends Filter{

        private final ArrayList<DataObject> filterData;
        private final CustomAdapter customAdapter;

        public CustomFilter(ArrayList<DataObject> filterData, CustomAdapter customAdapter) {
            this.customAdapter = customAdapter;
            this.filterData = filterData;
        }

        @Override
       protected FilterResults performFiltering(CharSequence constraint) {
           FilterResults results = new FilterResults();
           if (constraint != null && constraint.length()>0){
               constraint = constraint.toString().toUpperCase();
               ArrayList<DataObject> filteredObject = new ArrayList<DataObject>();

               for (int i =0; i<filterData.size(); i++){
                   if (filterData.get(i).getWord().toUpperCase().contains(constraint)){
                       DataObject d = new DataObject(filterData.get(i).getWord(), filterData.get(i).getType(),
                               filterData.get(i).getDefinition(),filterData.get(i).getExample());
                       filteredObject.add(d);
                   }
               }
              results.count = filteredObject.size();
              results.values = filteredObject;
           }else {
               results.count = filterData.size();
               results.values = filterData;
           }
           return results;
       }

       @Override
       protected void publishResults(CharSequence constraint, FilterResults results) {
        dataObjects = (ArrayList<DataObject>) results.values;
           notifyDataSetChanged();
       }
   }

}