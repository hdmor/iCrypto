package com.i.crypto.presentation.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.i.crypto.data.remote.dto.TeamMember
import com.i.crypto.presentation.coin_detail.components.CoinTag
import com.i.crypto.presentation.coin_detail.components.TeamListItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(viewModel: CoinDetailViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        state.coin?.let { coin ->

            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(2f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = coin.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Tags", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        coin.tags.forEach {
                            CoinTag(tag = it)
                            Spacer(modifier = Modifier
                                .width(4.dp)
                                .height(38.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Team members", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(coin.team) { teamMember: TeamMember ->
                    TeamListItem(teamMember = teamMember, modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
                    Divider()
                }

            }
        }
        if (state.error.isNotBlank())
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .align(Alignment.Center)
            )
        if (state.isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}