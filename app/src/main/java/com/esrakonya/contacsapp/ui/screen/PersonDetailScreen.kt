package com.esrakonya.contacsapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.esrakonya.contacsapp.data.model.Person
import com.esrakonya.contacsapp.ui.viewmodel.PersonViewModel
import com.esrakonya.contacsapp.ui.widget.PersonOutlinedTextField
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailScreen(
    person: Person,
    navController: NavController
) {
    val viewModel: PersonViewModel = viewModel()
    val localFocusManager = LocalFocusManager.current

    val tfPersonName = remember { mutableStateOf("") }
    val tfPersonSurname = remember { mutableStateOf("") }
    val tfPersonPhoneNumber = remember { mutableStateOf("") }

    LaunchedEffect(
        key1 = true
    ) {
        tfPersonName.value = person.personName ?: ""
        tfPersonSurname.value = person.personSurname ?: ""
        tfPersonPhoneNumber.value = person.personPhoneNumber ?: ""
    }

    Scaffold(
        topBar = {TopAppBar(title = { Text(text = "Contact Update", fontSize = 20.sp) })}
    ) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(contentPadding).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PersonOutlinedTextField(
                valueText = tfPersonName.value,
                onValueChange = {
                    tfPersonName.value = it.trim()
                },
                labelText = "Contact Name",
                placeHolderText = "",
                keyboardType = KeyboardType.Text
            )
            PersonOutlinedTextField(
                valueText = tfPersonSurname.value,
                onValueChange = {
                    tfPersonSurname.value = it.trim()
                },
                labelText = "Contact Surname",
                placeHolderText = "",
                keyboardType = KeyboardType.Text
            )
            PersonOutlinedTextField(
                valueText = tfPersonPhoneNumber.value,
                onValueChange = {
                    tfPersonPhoneNumber.value = it.trim()
                },
                labelText = "Contact Phone Number",
                placeHolderText = "",
                keyboardType = KeyboardType.Phone
            )

            Button(
                onClick = {
                    viewModel.updatePersonData(
                        personID = UUID.randomUUID().toString(),
                        personName = tfPersonName.value,
                        personSurname = tfPersonSurname.value,
                        personPhoneNumber = tfPersonPhoneNumber.value
                    )
                    navController.popBackStack()
                    localFocusManager.clearFocus()
                },
                modifier = Modifier.padding(top = 20.dp).size(200.dp, 50.dp)
            ) {
                Text(text = "UPDATE")
            }
        }
    }
}