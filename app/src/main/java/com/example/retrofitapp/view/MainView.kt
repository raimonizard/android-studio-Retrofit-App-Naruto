package com.example.retrofitapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.retrofitapp.model.Character
import com.example.retrofitapp.model.Data
import com.example.retrofitapp.viewmodel.APIViewModel

@Composable
fun MyRecyclerView(myViewModel: APIViewModel) {
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val characters: Data by myViewModel.characters.observeAsState(Data(emptyList(), 0, 0, 0))
    myViewModel.getCharacters()
    if (showLoading) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary
            )
        }
    } else {
        LazyColumn {
            items(characters.characters) {
                CharacterItem(character = it)
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: Character) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            GlideImage(
                model = character.images[0],
                contentDescription = "Character Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize()
            )
        }
    }
}
