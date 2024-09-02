package com.example.merge


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merge.presentation.state.SortViewModel
import com.example.merge.ui.theme.MergeTheme
import com.example.merge.ui.theme.dark

class MainActivity : ComponentActivity() {

    private val sortViewModel by viewModels<SortViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MergeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center,

                    ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter),
                        verticalArrangement = Arrangement.spacedBy(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        itemsIndexed(
                            sortViewModel.sortInfoUiItemList
                        ) { index, sortInfoUiItem ->
                            val depthParts = sortInfoUiItem.sortParts
                            if (index == 0) {
                                Text(
                                    "Divide List into Halves",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(bottom = 20.dp)
                                        .padding(top = 40.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start
                                )
                            }

                            if (index == 4) {
                                Text(
                                    "Merge Halves Back",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(bottom = 5.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    "Checking For the Least & Greatest Element",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(bottom = 20.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                for (part in depthParts) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                                        modifier = Modifier
                                            .padding(
                                                start = if (depthParts.indexOf(part) == 0) 0.dp else 10.dp
                                            )
                                            .background(sortInfoUiItem.color)
                                            .padding(7.dp)

                                    ) {
                                        for (numberInformation in part) {
                                            if (part.indexOf(numberInformation) != part.size - 1) {
                                                Text(
                                                    text = "$numberInformation |",
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black,
                                                    fontSize = 19.sp
                                                )
                                            } else {
                                                Text(
                                                    text = "$numberInformation",
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black,
                                                    fontSize = 19.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(dark)
                            .padding(15.dp)
                            .align(Alignment.BottomCenter),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp)

                    ) {

                        Text(
                            text = "${sortViewModel.listToSort}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White

                        )

                        Button(
                            onClick = {
                                sortViewModel.startSorting()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .height(50.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White, // Sets the button background color to black
                                contentColor = Color.Black    // Sets the text color to white for contrast
                            )
                        ) {
                            Text(
                                text = "Visualize Merge Sort",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }


                    }


                }


            }


        }
    }
}

