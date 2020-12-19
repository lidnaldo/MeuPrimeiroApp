package br.iesb.meuprimeiroapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import br.iesb.meuprimeiroapp.databinding.FragmentListaPersonagemBinding
import br.iesb.meuprimeiroapp.domain.PersonagemData
import br.iesb.meuprimeiroapp.viewmodel.ApiViewModel

class ListaPersonagemFragment : Fragment() {

    private lateinit var binding: FragmentListaPersonagemBinding
    private val viewModel: ApiViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaPersonagemBinding.inflate(inflater, container, false)
        binding.varFragment = this
        binding.lifecycleOwner = this


/*  1. MANEIRA DE CHAMAR VIA chamarAPI() -- setOnClickListener
       binding.btChamarAPI.setOnClickListener{ chamarAPI() }
    2. MANEIRA DE CHAMAR VIA chamarAPI() de dentro do XML -- fragment_lista_personagem.xml
       android:onClick="@{ () -> varFragment.chamarAPI() }"
*/

        //observa quando houver mudança de caonteúdo
        viewModel.resultadoParaTela.observe(viewLifecycleOwner){ lista ->
            mostratResultadoAPI(lista)
        }
        return binding.root
    }


    private fun mostratResultadoAPI(lista: List<PersonagemData>){
//        var primeiroObjetoLista = lista.first()
        var primeiroObjetoLista = lista[3]
        var str = "nome: ${primeiroObjetoLista.nome} " +
                "genero: ${primeiroObjetoLista.genero} " +
                "tipo: ${primeiroObjetoLista.tipo}"

        Toast.makeText(context, str, Toast.LENGTH_LONG).show()
    }


    fun chamarAPI() {
        viewModel.chamarAPI()
    }

}