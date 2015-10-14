package application.model.cards;


class Card {

    private int number;
    private Question question;
    private Category category;

    public Card() {
        this.question = new Question();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
