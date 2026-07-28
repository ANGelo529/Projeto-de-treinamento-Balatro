class carta {
    constructor(numero, naipe, tipo) {
        this.numero = numero
        this.naipe = naipe 
        this.tipo = tipo
    }
}
function redenrizarCarta(objetoCarta) {
    //Cria a div da carta (somente no js)
    const visualCarta = document.createElement('div')
    const mesa = document.getElementById('jogando')
    visualCarta.classList.add('carta')

    
    if (objetoCarta.naipe == "espadas" || objetoCarta.naipe == "paus") {
        visualCarta.classList.add('preto')
    }else{''
        visualCarta.classList.add('vermelho')
    }

    
     if (objetoCarta.naipe == "espadas") {
        visualCarta.classList.add('preto')
          visualCarta.innerHTML = `
            <span>${objetoCarta.numero}</span>
            <span>♠</span>
            `;
        
    }else if(objetoCarta.naipe == "ouros"){
        visualCarta.classList.add('vermelho')
          visualCarta.innerHTML = `
            <span>${objetoCarta.numero}</span>
            <span>♦</span>
            `;
        
    }else if (objetoCarta.naipe == "paus") {
        visualCarta.innerHTML = `
            <span>${objetoCarta.numero}</span>
            <span>♣</span>
            `;
        visualCarta.classList.add('preto')
    }else {
        visualCarta.classList.add('vermelho')
         visualCarta.innerHTML = `
            <span>${objetoCarta.numero}</span>
            <span>♥</span>
            `;
    }

    if (mesa) {
        mesa.appendChild(visualCarta);
    }
    
}
// --- PAUS ♣ ---
const DoisPaus = new carta("2", "paus", "comun");
const TresPaus = new carta("3", "paus", "comun");
const QuatroPaus = new carta("4", "paus", "comun");
const CincoPaus = new carta("5", "paus", "comun");
const SeisPaus = new carta("6", "paus", "comun");
const SetePaus = new carta("7", "paus", "comun");
const OitoPaus = new carta("8", "paus", "comun");
const NovePaus = new carta("9", "paus", "comun");
const DezPaus = new carta("10", "paus", "comun");
const JPaus = new carta("J", "paus", "comun");
const QPaus = new carta("Q", "paus", "comun");
const KPaus = new carta("K", "paus", "comun");
const APaus = new carta("A", "paus", "comun");

// --- OUROS ♦ ---
const DoisOuros = new carta("2", "ouros", "comun");
const TresOuros = new carta("3", "ouros", "comun");
const QuatroOuros = new carta("4", "ouros", "comun");
const CincoOuros = new carta("5", "ouros", "comun");
const SeisOuros = new carta("6", "ouros", "comun");
const SeteOuros = new carta("7", "ouros", "comun");
const OitoOuros = new carta("8", "ouros", "comun");
const NoveOuros = new carta("9", "ouros", "comun");
const DezOuros = new carta("10", "ouros", "comun");
const JOuros = new carta("J", "ouros", "comun");
const QOuros = new carta("Q", "ouros", "comun");
const KOuros = new carta("K", "ouros", "comun");
const AOuros = new carta("A", "ouros", "comun");

// --- COPAS ♥ ---
const DoisCopas = new carta("2", "copas", "comun");
const TresCopas = new carta("3", "copas", "comun");
const QuatroCopas = new carta("4", "copas", "comun");
const CincoCopas = new carta("5", "copas", "comun");
const SeisCopas = new carta("6", "copas", "comun");
const SeteCopas = new carta("7", "copas", "comun");
const OitoCopas = new carta("8", "copas", "comun");
const NoveCopas = new carta("9", "copas", "comun");
const DezCopas = new carta("10", "copas", "comun");
const JCopas = new carta("J", "copas", "comun");
const QCopas = new carta("Q", "copas", "comun");
const KCopas = new carta("K", "copas", "comun");
const ACopas = new carta("A", "copas", "comun");

// --- ESPADAS ♠ ---
const DoisEspadas = new carta("2", "espadas", "comun");
const TresEspadas = new carta("3", "espadas", "comun");
const QuatroEspadas = new carta("4", "espadas", "comun");
const CincoEspadas = new carta("5", "espadas", "comun");
const SeisEspadas = new carta("6", "espadas", "comun");
const SeteEspadas = new carta("7", "espadas", "comun");
const OitoEspadas = new carta("8", "espadas", "comun");
const NoveEspadas = new carta("9", "espadas", "comun");
const DezEspadas = new carta("10", "espadas", "comun");
const JEspadas = new carta("J", "espadas", "comun");
const QEspadas = new carta("Q", "espadas", "comun");
const KEspadas = new carta("K", "espadas", "comun");
const AEspadas = new carta("A", "espadas", "comun");

document.getElementById("botaoJogar").onclick = function(){
    document.getElementById("telaInicial").style.display = "none"
    document.getElementById("jogando").style.display = "block"
}

document.getElementById("botaocreditos").onclick = function(){
    document.getElementById("telaInicial").style.display = "none"
    document.getElementById("creditos").style.display = "block"
}

document.getElementById("botaoinstrucoes").onclick = function(){
    document.getElementById("telaInicial").style.display = "none"
    document.getElementById("instrucoes").style.display = "block"
}