package com.example.myrecipeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import java.lang.reflect.Modifier as Modifier1

@Composable
fun RecipeScreen(modifier: Modifier1 = Modifier, viewState: MainViewModel.RecipeState, navigateToDetail: (Category) -> Unit) {



    Box(modifier = Modifier1.fillMaxSize()) {

        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(text = "ERROR OCCURED")
            }

            else ->{
                CategoryScreen(categories = viewState.list, navigateToDetail)
            }
        }

    }
}

    @Composable
    fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category) -> Unit) {
        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier1.fillMaxSize()){
            items(categories){
                category->
                CategoryItem(category = category, navigateToDetail)
            }
        }
    }

    @Composable
    fun CategoryItem(category: Category,
                     navigateToDetail : (Category) -> Unit){
        Column(modifier = Modifier1
            .padding(8.dp)
            .fillMaxSize()
            .clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally)
        {
            Image(painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
                contentDescription = null,
                modifier = Modifier1
                    .fillMaxSize()
                    .aspectRatio(1f))

            Text(text = category.strCategory,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier1.padding(4.dp)
            )
            
        }
    }

