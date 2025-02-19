package com.esrakonya.contacsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esrakonya.contacsapp.data.model.Person
import com.esrakonya.contacsapp.data.repository.PersonRepository

class PersonViewModel : ViewModel() {
    private val repository = PersonRepository()

    var personList = MutableLiveData<List<Person>>()

    init {
        repository.getAllPerson()
        personList = repository.personList
    }

    fun searchPersonData(searchText: String) {
        repository.searchPerson(searchText)
    }

    fun registerPersonData(
        personName: String,
        personSurname: String,
        personPhoneNumber: String
    ) {
        repository.addPerson(personName, personSurname, personPhoneNumber)
    }

    fun updatePersonData(
        personID: String,
        personName: String,
        personSurname: String,
        personPhoneNumber: String
    ) {
        repository.updatePerson(personID, personName, personSurname, personPhoneNumber)
    }

    fun deletePersonData(
        personID: String
    ) {
        repository.deletePerson(personID)
    }
}