import fetchContact from "@/api/contact/fetchContact";
import ContactForm from "@/components/ui/forms/ContactForm";

export default async function UpdateContactPage({ params }) {
    const { id } = await params;
    const contact = await fetchContact(id);

    return (
        <ContactForm initialData={contact} />
    )
}
