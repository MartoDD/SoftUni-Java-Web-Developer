function solve(input){

    let evenSum=0;
    let oddSum=0;

    for(let i=0; i<input.length; i++)

        if(input[i]%2===0){
            evenSum+=input[i];
        }else{
            oddSum+=input[i];
        }

    console.log(evenSum-oddSum);

}

solve([3,5,7,9])