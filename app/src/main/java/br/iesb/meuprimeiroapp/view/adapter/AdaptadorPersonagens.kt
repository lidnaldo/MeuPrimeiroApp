package br.iesb.meuprimeiroapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.iesb.meuprimeiroapp.R
import br.iesb.meuprimeiroapp.databinding.ItemDoPersonagemBinding
import br.iesb.meuprimeiroapp.domain.PersonagemData

class AdaptadorPersonagens(val lista: List<PersonagemData>) :
    RecyclerView.Adapter<AdaptadorPersonagens.GuardadorDeDadosPersonagem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardadorDeDadosPersonagem {
        val instanciaDoXML = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_do_personagem, parent, false)
        val guardador = GuardadorDeDadosPersonagem(instanciaDoXML)
        return guardador
    }

    override fun onBindViewHolder(holder: GuardadorDeDadosPersonagem, position: Int) {
        val binding = holder.binding
        val p = lista[position]
        binding.personagem = p
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return lista.size  //quantidade de itens carregados na lista
    }

    inner class GuardadorDeDadosPersonagem(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ItemDoPersonagemBinding = ItemDoPersonagemBinding.bind(v)


    }
}