class carta {
    constructor(numero, naipe, tipo) {
        this.numero = numero
        this.naipe = naipe 
    }
}
function redenrizarCarta() {
    //Cria a div da carta (somente no js)
    const visualCarta = document.createElement('div')
    const rei = new carta("K", "paus")
    const mesa = document.getElementById('jogando')
    visualCarta.classList.add('carta')
    if (rei.naipe == "espadas" || rei.naipe == "paus") {
        visualCarta.classList.add('preto')
    }else{
        visualCarta.classList.add('vermelho')
    }
    
    visualCarta.innerHTML = `
        <span>${rei.numero}</span>
        <span>♣</span>
    `;

    

    if (mesa) {
        mesa.appendChild(visualCarta);
    }
    
    
}
redenrizarCarta();