package com.example.crudkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudkotlin.databinding.ActivityAddNoteBinding
import com.example.crudkotlin.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NotesDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditorText.text.toString()
            val note = Note(0,title,content)
            db.inserNote(note)
            finish()
            Toast.makeText(this,"note saved",Toast.LENGTH_SHORT).show()
        }

    }
}