package br.iesb.meuprimeiroapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.iesb.meuprimeiroapp.databinding.FragmentListaPersonagemBinding
import br.iesb.meuprimeiroapp.domain.PersonagemData
import br.iesb.meuprimeiroapp.view.adapter.AdaptadorPersonagens
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

        // configuração do recycleView
        binding.rvPersonagens.layoutManager = LinearLayoutManager(context)

        return binding.root
    }


    private fun mostratResultadoAPI(lista: List<PersonagemData>){
        //RECYCLEVIEW <--> ADAPTADOR <--> FONTE DE DADOS
        val adaptador = AdaptadorPersonagens(lista)

        binding.rvPersonagens.adapter = adaptador

    }


    fun chamarAPI() {
        viewModel.chamarAPI()
    }

}