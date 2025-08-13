export const QuestionType = Object.freeze({
    YES_NO: { code: 2000, description: 'Sim ou Não' },
    RATING_SCALE: { code: 2001, description: 'Escala de Avaliação' },
    OPEN_TEXT: { code: 2002, description: 'Texto Aberto' },
    SINGLE_CHOICE: { code: 2003, description: 'Única Escolha' },
    MULTIPLE_CHOICE: { code: 2004, description: 'Múltipla Escolha' }
});

export function getQuestionDescriptionByCode(code) {
    return Object.values(QuestionType).find(type => type.code === code).description || null;
}
