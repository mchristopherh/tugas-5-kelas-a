package com.example.room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.entity.User;
import com.example.room.viewmodel.UserViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private EditText editTextName;
    private Button buttonAdd;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Pastikan layout memiliki semua view yang diperlukan

        recyclerView = findViewById(R.id.recyclerView);
        editTextName = findViewById(R.id.editTextName);
        buttonAdd = findViewById(R.id.buttonAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
            }
        });

        buttonAdd.setOnClickListener(v -> {
            String newName = editTextName.getText().toString().trim();

            if (!newName.isEmpty()) {
                User user = new User(newName);
                userViewModel.insert(user);
                editTextName.setText("");
                Toast.makeText(MainActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Tolong masukkan Nama", Toast.LENGTH_SHORT).show();
            }
        });






        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                User userToDelete = adapter.getUserAtPosition(position);

                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Hapus Data")
                        .setMessage("Apakah Anda yakin ingin menghapus data ini?")
                        .setPositiveButton("Ya", (dialog, which) -> {
                            userViewModel.delete(userToDelete);
                            Toast.makeText(MainActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Tidak", (dialog, which) -> {
                            adapter.notifyItemChanged(position);
                        })
                        .setCancelable(false)
                        .show();
            }
        }).attachToRecyclerView(recyclerView);
    }
}
