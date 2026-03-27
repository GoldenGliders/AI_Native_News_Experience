async function askQuestion() {
    const questionInput = document.getElementById("questionInput");
    const answerBox = document.getElementById("answerBox");

    const question = questionInput.value;

    if (!question) {
        alert("Please enter a question");
        return;
    }

    const response = await fetch("/api/news/question", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            question: question
        })
    });

    const data = await response.json();

    answerBox.innerText = data.answer;
}