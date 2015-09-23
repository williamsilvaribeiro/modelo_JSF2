/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


// Funcoes js que formatam campos nas mascaras correspondentes. By Will //

function mascara(o,f){
    v_obj=o;
    v_fun=f;
    setTimeout("execmascara()",1);
}

function execmascara(){
    v_obj.value=v_fun(v_obj.value);
}

/* Função que padroniza datas. Ex.: 01/01/2011 */
/* Campo -> MaxLength = 10 */
function mascaraData(valor){
    valor = valor.replace(/\D/g,"");
    valor = valor.replace(/(\d{2})(\d)/,"$1/$2");
    valor = valor.replace(/(\d{2})(\d)/,"$1/$2");
    return valor;
}

/* Função que padroniza valores em reais. Ex.: 1.431.232.233,00 */
/* Campo -> MaxLength = 16 */
function mascaraValorReais(valor){
    valor = valor.replace(/\D/g,"");                                         // Remove tudo o que não é dígito
    valor = valor.replace(/(\d{2})$/,",$1");                                 // Coloca a virgula
    valor = valor.replace(/(\d+)(\d{3},\d{2})$/g,"$1.$2");                   // Coloca o primeiro ponto (milhar)
    valor = valor.replace(/(\d+)(\d{3})(.\d{3},\d{2})$/g,"$1.$2$3");         // Coloca o segundo ponto  (milhão)
    valor = valor.replace(/(\d+)(\d{3})(.\d{3}.\d{3},\d{2})$/g,"$1.$2$3");   // Coloca o terceiro ponto (bilhão)
    return valor;
}

/* Função que padroniza telefone. Ex.: (11) 4184-1241 */
/* Campo -> MaxLength = 14 */
function mascaraTelefone(valor){
    valor = valor.replace(/\D/g,"");
    valor = valor.replace(/^(\d\d)(\d)/g,"($1) $2");
    valor = valor.replace(/(\d{4})(\d)/,"$1-$2");
    return valor;
}

/* Função que padroniza CEP. Ex.: 72000-000 */
/* Campo -> MaxLength = 9 */
function mascaraCep(valor){
    valor = valor.replace(/D/g,"");
    valor = valor.replace(/^(\d{5})(\d)/,"$1-$2");
    return valor;
}

/* Função que padroniza CPF. Ex.: 123.456.789-00 */
/* Campo -> MaxLength = 14 */
function mascaraCpf(valor){
    valor = valor.replace(/\D/g,"");
    valor = valor.replace(/(\d{3})(\d)/,"$1.$2");
    valor = valor.replace(/(\d{3})(\d)/,"$1.$2");
    valor = valor.replace(/(\d{3})(\d{1,2})$/,"$1-$2");
    return valor;
}

/* Função que padroniza CNPJ. Ex.: 12.123.123/0001-1234 */
/* Campo -> MaxLength = 18 */
function mascaraCnpj(valor){
    valor = valor.replace(/\D/g,"");
    valor = valor.replace(/^(\d{2})(\d)/,"$1.$2");
    valor = valor.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3");
    valor = valor.replace(/\.(\d{3})(\d)/,".$1/$2");
    valor = valor.replace(/(\d{4})(\d)/,"$1-$2");
    return valor;
}

//////////////////////////////////////////////////////////////////////////

function somenteNumeros(campo){
    var valor = campo.value;

    valor = valor.replace(/\D/g,""); //Remove tudo o que não é número [0,9]

    campo.value = valor;
}

function IE(){
    var browser = navigator.userAgent;
    if (browser.indexOf("MSIE")>0) return true;
    return false;
}

function getTecla(evento){
    if (IE()) return evento.keyCode;
    return evento.which;
}
